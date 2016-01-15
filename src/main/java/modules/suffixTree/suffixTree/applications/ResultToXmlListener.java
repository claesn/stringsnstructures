package modules.suffixTree.suffixTree.applications;

import java.util.ArrayList;

import modules.suffixTree.suffixTree.SuffixTree;
import modules.suffixTree.suffixTree.node.GeneralisedSuffixTreeNode;
import modules.suffixTree.suffixTree.node.textStartPosInfo.TextStartPosInfo;

/**
 * A TreeWalkerListener that outputs the trees nodes directly to an
 * XmlPrintWriter.
 */
public class ResultToXmlListener implements ITreeWalkerListener {

	// the XmlPrintWriter to use for writing 
	private final XmlPrintWriter out;
	
	// the SuffixTree to serialize
	private final SuffixTree suffixTree;

	// cstr
	public ResultToXmlListener(SuffixTree suffixTree, XmlPrintWriter o) {
		this.suffixTree = suffixTree;
		this.out = o;
	}

	/**
	 * Nothing is done on the entry action.
	 */
	@Override
	public void entryaction(int nodeNr, int level) {
		// do nothing
	}

	@Override
	public void exitaction(int nodeNr, int level) {
		
		final String label = suffixTree.edgeString(nodeNr);

		GeneralisedSuffixTreeNode node = ((GeneralisedSuffixTreeNode) suffixTree.nodes[nodeNr]);
		ArrayList<TextStartPosInfo> nodeList = node.getStartPositionOfSuffix();

		// node(nr)
		out.printTag("node", true, 1, true);
		out.printTag("number", true, 2, false);
		out.printInt(nodeNr);
		out.printTag("number", false, 0, true);
		// edge label
		out.printTag("label", true, 2, false);
		out.print(label);
		out.printTag("label", false, 0, true);

		int freq = nodeList.size();
		out.printTag("frequency", true, 2, false);
		out.print(freq);
		out.printTag("frequency", false, 0, true);
		out.printTag("type", true, 2, true);

		for (int i = 0; i < freq; i++) {
			out.printTag("patternInfo", true, 3, true);
			TextStartPosInfo inf = nodeList.get(i);

			out.printTag("typeNr", true, 4, false);
			out.printInt(inf.unit);
			out.printTag("typeNr", false, 0, true);
			out.printTag("pattern", true, 4, false);
			out.printInt(inf.text);
			out.printTag("pattern", false, 0, true);
			out.printTag("startpos", true, 4, false);
			out.printInt(inf.startPositionOfSuffix);
			out.printTag("startpos", false, 0, true);
			out.printTag("patternInfo", false, 3, true);
		}
		out.printTag("type", false, 2, true);
		out.printTag("node", false, 1, true);
	}
}