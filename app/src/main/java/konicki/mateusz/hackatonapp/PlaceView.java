package konicki.mateusz.hackatonapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import konicki.mateusz.hackatonapp.model.Beacon;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class PlaceView extends View {

    Beacon beacon;

    public PlaceView(Context context) {
        super(context);
    }

    public PlaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PlaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.aluekartt);
        bitmap = Bitmap.createScaledBitmap(bitmap, canvas.getWidth(), canvas.getHeight(), true);
        canvas.drawBitmap(bitmap, 0, 0, null);
        drawPoint(beacon, canvas);

    }

    private void drawPoint(Beacon beacon, Canvas canvas) {
        if (beacon == null)
            return;

        Paint paint = generatePaint(Paint.Style.FILL, Color.BLUE, 80);
        canvas.drawCircle(beacon.getX() * canvas.getWidth(), beacon.getY() * canvas.getHeight(), 17, paint);

        Paint paint2 = generatePaint(Paint.Style.FILL, Color.RED, 80);
        canvas.drawCircle(beacon.getX() * canvas.getWidth(), beacon.getY() * canvas.getHeight(), 12, paint2);

    }

    private Paint generatePaint(Paint.Style style, int color, int width) {
        Paint paint = new Paint();
        paint.setStyle(style);
        paint.setColor(color);
        return paint;
    }

    public void drawBeaconOnMap(Beacon beacon) {
        this.beacon = beacon;
        invalidate();
    }

}
