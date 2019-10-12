package id.revan.mvvmwithdagger.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.revan.mvvmwithdagger.R;

public class LayoutHelper {
    private Context context;

    public LayoutHelper(Context context) {
        this.context = context;
    }

    public void showLoader(ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_loader, null, false);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
    }

    public void showLayoutError(ViewGroup viewGroup, String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_error, null, false);
        TextView tvError = view.findViewById(R.id.tv_error);
        tvError.setText(message);

        viewGroup.removeAllViews();
        viewGroup.addView(view);
    }
}
