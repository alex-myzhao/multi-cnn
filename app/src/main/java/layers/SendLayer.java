package layers;

public class SendLayer implements LayerInterface {
    public SendLayer() {

    }

    @Override
    public Object compute(Object input) {
        return input;
    }
}
