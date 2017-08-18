package anuj.wifidirect.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ExtendListView extends ListView {
    public ExtendListView(Context context) {
        super(context);
    }

    public ExtendListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtendListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST));
    }
}
