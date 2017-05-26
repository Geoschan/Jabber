package enterprise.lgm.jabber;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Marvin on 26.05.2017.
 */

public class AlertBuilder {
    public static void alertSingleChoice(String message, String choice1, Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.setPositiveButton(
                choice1,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertRepeat = builder.create();
        alertRepeat.show();

    }
}
