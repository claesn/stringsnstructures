package modules.suffixTreeV2;

import java.util.Set;
import java.util.TreeSet;

import modules.suffixTreeV2.BaseSuffixTree;

public class ResultLabelListListener implements ITreeWalkerListener {
	
	private final BaseSuffixTree suffixTree;
	
	private final TreeSet<String> labels;
	
	public ResultLabelListListener(BaseSuffixTree suffixTree) {
		this.suffixTree = suffixTree;
		this.labels = new TreeSet<String>();
	}

	@Override
	public void entryaction(int nodeNr, int level) {
		if (nodeNr != this.suffixTree.getRoot()) {
			labels.add(this.suffixTree.edgeString(nodeNr));
		}
	}

	@Override
	public void exitaction(int nodeNr, int level) {
		// TODO Auto-generated method stub
	}
	
	public Set<String> getLabels() {
		return labels;
	}

}
