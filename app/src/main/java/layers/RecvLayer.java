package layers;

public class RecvLayer implements LayerInterface {
    private boolean mInit;

    public RecvLayer(boolean init) {
        this.mInit = init;
    }

    @Override
    public Object compute(Object input) {
        if (this.mInit) {
            // initialize

        } else {
            // update spec data
        }
        return null;
    }
}
