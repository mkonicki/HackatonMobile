package konicki.mateusz.hackatonapp.base;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import konicki.mateusz.hackatonapp.R;

/**
 * Created by Mateusz on 07.04.2017.
 */

public class AlertDialogHelper {



    public AlertDialog createAlertDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = getBuilder(context, title, message);
        return builder.create();
    }

    public AlertDialog.Builder getBuilder(Context context, String title, String message) {
        return new AlertDialog.Builder(context, R.style.dialogTheme)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true);
    }


}
