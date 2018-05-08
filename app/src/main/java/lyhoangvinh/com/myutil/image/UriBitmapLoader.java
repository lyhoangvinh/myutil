package lyhoangvinh.com.myutil.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import lyhoangvinh.com.myutil.rx.RxLoader;


/**
 * Created by vinh on 10/12/2017.
 *
 */

public class UriBitmapLoader extends RxLoader<Uri, Bitmap> {

    protected final Context mContext;
    protected int maxSize = 2000;

    public UriBitmapLoader(Context context, Uri source) {
        super(source);
        this.mContext = context;
    }

    public UriBitmapLoader maxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    @Override
    protected Bitmap load(Uri source) {
        return BitmapUtils.decodeUriToScaledBitmap(mContext, source, 0, maxSize, maxSize);
    }
}
