package Controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import chriatian.tuanhuydev.todo.R;

public class showDialert {
    private AlertDialog.Builder alertDialog;
    public showDialert(Context context, String title , String message) {
        alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }
}
