package modules.basemodules;

import java.io.PrintStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.parallelization.CallbackReceiver;

import modules.BytePipe;
import modules.CharPipe;
import modules.ModuleImpl;

/**
 * Writes any input to console
 * @author Marcel Boeing
 *
 */
public class ConsoleWriterModule extends ModuleImpl {
	
	public ConsoleWriterModule(CallbackReceiver callbackReceiver, Properties properties) throws Exception {
		super(callbackReceiver, properties);
		
		// Define I/O
		this.getSupportedInputs().add(BytePipe.class);
		this.getSupportedInputs().add(CharPipe.class);
		
		// Add default values
		this.getPropertyDefaultValues().put(ModuleImpl.PROPERTYKEY_NAME, "Console Writer");
	}

	@Override
	public boolean process() throws Exception {
		
		/*
		 * read from both input channels (reader/inputstream) -- if the first one
		 * is not connected to another module's output, it will throw an exception
		 * (that we will catch)
		 */
		try {
			// Output stream
			PrintStream out = System.out;
			
			// Define input buffer
			byte[] buffer = new byte[1024];
			
			// Read file data into buffer and write to outputstream
			int readBytes = this.getInputBytePipe().getInput().read(buffer);
			while (readBytes>0){
				out.write(buffer, 0, readBytes);
				readBytes = this.getInputBytePipe().getInput().read(buffer);
			}
			
			// Log message
			Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "Streamed output onto console");
			
		} catch (Exception e){
			/* The inputstream does not seem to be connected -- try reader/writer instead */
			
			// Output writer
			PrintStream out = System.out;
			
			// Define input buffer
			char[] buffer = new char[1024];
			
			// Read file data into buffer and output to writer
			int readChars = this.getInputCharPipe().getInput().read(buffer);
			while(readChars != -1){
				for (int i=0; i<readChars; i++){
					if (buffer[i]<0)
						break;
					out.print(buffer[i]);
				}
				readChars = this.getInputCharPipe().getInput().read(buffer);
			}
			
			// Log message
			Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "Wrote output to console");
		}
		
		// Success
		return true;
	}

}