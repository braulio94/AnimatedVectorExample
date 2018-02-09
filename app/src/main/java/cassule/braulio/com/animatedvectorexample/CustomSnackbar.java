package cassule.braulio.com.animatedvectorexample;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by Braulio on 2/9/2018.
 **/

public class CustomSnackbar {

    private LayoutInflater layoutInflater;
    private int layout;
    private int background;
    private View contentView;
    private LENGTH duration;
    private boolean swipe;

    private Snackbar snackbar;
    private Snackbar.SnackbarLayout snackbarView;

    private CustomSnackbar(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.duration = LENGTH.LONG;
        this.background = -1;
        this.layout = -1;
        this.swipe = true;
    }

    public static CustomSnackbar Builder(Context context) {
        return new CustomSnackbar(context);
    }

    public CustomSnackbar layout(int layout) {
        this.layout = layout;
        return this;
    }

    public CustomSnackbar background(int background) {
        this.background = background;
        return this;
    }

    public CustomSnackbar duration(LENGTH duration) {
        this.duration = duration;
        return this;
    }

    public CustomSnackbar swipe(boolean swipe) {
        this.swipe = swipe;
        return this;
    }

    public CustomSnackbar build(View view) {
        if (view == null) throw new CustomSnackbarException("view can not be null");
        if (layout == -1) throw new CustomSnackbarException("layout must be setted");
        switch (duration) {
            case INDEFINITE:
                snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);
                break;
            case SHORT:
                snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);
                break;
            case LONG:
                snackbar = Snackbar.make(view, "", 7000);
                break;
        }
        snackbarView = (Snackbar.SnackbarLayout) snackbar.getView();

        if (!swipe) {
            snackbarView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    snackbarView.getViewTreeObserver().removeOnPreDrawListener(this);
                    ((CoordinatorLayout.LayoutParams) snackbarView.getLayoutParams()).setBehavior(null);
                    return true;
                }
            });
        }

        snackbarView.setPadding(0, 0, 0, 0);
        if (background != -1) snackbarView.setBackgroundResource(background);
        TextView text = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        text.setVisibility(View.INVISIBLE);
        TextView action = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_action);
        action.setVisibility(View.INVISIBLE);
        contentView = layoutInflater.inflate(layout, null);
        snackbarView.addView(contentView, 0);
        return this;
    }

    public void show() {
        snackbar.show();
    }

    public boolean isShowing() {
        return snackbar != null && snackbar.isShown();
    }

    public void dismiss() {
        if (snackbar != null) snackbar.dismiss();
    }

    public View getContentView() {
        return contentView;
    }

    public enum LENGTH {
        INDEFINITE, SHORT, LONG
    }

    public class CustomSnackbarException extends RuntimeException {

        public CustomSnackbarException(String detailMessage) {
            super(detailMessage);
        }

    }

}
