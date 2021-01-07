package com.ogqcorp.market.ontology.res.verify;

import java.util.Arrays;

public class Verify
{
    private String root;
    private int size;
    private int blockheight;
    private int leafHeight;
    private int index;
    private String txHash;
    private String[] proof;

    public Verify() {}

    public String getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public int getBlockheight() {
        return blockheight;
    }

    public int getLeafHeight() { return leafHeight; }

    public int getIndex() {
        return index;
    }

    public String getTxHash() { return txHash; }

    public String[] getProof() {
        return proof;
    }

    @Override
    public String toString() {
        return String.format(
            "block height: %d, leaf height: %d, size: %d, index: %d, root: %s, txHash: %s, proof: %s",
            blockheight, leafHeight, size, index, root, txHash, Arrays.toString(proof)
        );
    }
}
