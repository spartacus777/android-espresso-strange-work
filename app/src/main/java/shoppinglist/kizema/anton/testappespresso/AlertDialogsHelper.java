//package shoppinglist.kizema.anton.testappespresso;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.res.Configuration;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.res.Configuration;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//
///**
// * Helps manipulating alert - get common dialog or directly show the alert
// */
//public class AlertDialogsHelper {
//
//    public static final float dialogWidthRatio = 0.85f;
//
//    private AlertDialogsHelper() {
//    }
//
//    /**
//     * Returns info {@code Dialog} with given message and button label
//     *
//     * @param activity {@code Activity} in which alert will be executed
//     * @param msg      {@code String} message of the dialog
//     * @return prepared {@code Dialog}
//     */
//    public static Dialog getMessageDialog(final Activity activity, final String msg) {
//
//        final Dialog dialog = new Dialog(activity);
//        dialog.setContentView(R.layout.dialog_alert);
//        setDialogWidth(activity, dialog);
//
//        TextView dialogTitle = (TextView) dialog.findViewById(R.id.text);
//        dialogTitle.setText(msg);
//
//        Button dialogButton = (Button) dialog.findViewById(R.id.buttonAccept);
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.setCanceledOnTouchOutside(true);
//
//        return dialog;
//    }
//
//    /**
//     * Returns confirmation dialog {@code Dialog} with given message
//     *
//     * @param activity               {@code Activity} in which alert will be executed
//     * @param msg                    {@code String} message of the dialog
//     * @param confirmOnClickListener {@code OnClickListener} for confirmation button click
//     * @param declineOnClickListener {@code OnClickListener} for declining button click
//     * @return prepared {@code Dialog}
//     */
//    public static Dialog getConfirmDeclineGeneralDialog(
//            final Activity activity,
//            final String msg,
//            final View.OnClickListener confirmOnClickListener,
//            final View.OnClickListener declineOnClickListener) {
//
//        final Dialog dialog = new Dialog(activity);
//        dialog.setContentView(R.layout.dialog_confirm_decline);
//        setDialogWidth(activity, dialog);
//
//        TextView dialogTitle = (TextView) dialog.findViewById(R.id.text);
//        dialogTitle.setText(msg);
//
//        Button dialogButtonAccept = (Button) dialog.findViewById(R.id.buttonAccept);
//        dialogButtonAccept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//
//                if (confirmOnClickListener != null) {
//                    confirmOnClickListener.onClick(v);
//                }
//            }
//        });
//
//        Button dialogButtonDecline = (Button) dialog.findViewById(R.id.buttonDecline);
//        dialogButtonDecline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//
//                if (declineOnClickListener != null) {
//                    declineOnClickListener.onClick(v);
//                }
//            }
//        });
//
//        dialog.setCanceledOnTouchOutside(true);
//
//        return dialog;
//    }
//
//    /**
//     * Returns select dialog
//     *
//     * @param activity      {@code Activity} in which dialog will be executed
//     * @param items         msg {@code String[]} select dialog items
//     * @param clickListener item click listener
//     * @return prepared {@code Dialog}
//     */
//    public static Dialog getSelectDialog(
//            final Activity activity,
//            final String[] items,
//            final AdapterView.OnItemClickListener clickListener) {
//
//        final Dialog dialog = new Dialog(activity);
//        dialog.setContentView(R.layout.dialog_select);
//        setDialogWidth(activity, dialog);
//
//        ListView dialogListView = (ListView) dialog.findViewById(R.id.listView);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.dialog_item_select, R.id.text, items);
//
//        dialogListView.setAdapter(adapter);
//        dialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                dialog.dismiss();
//                clickListener.onItemClick(parent, view, position, id);
//            }
//        });
//
//        dialog.setCanceledOnTouchOutside(true);
//
//        return dialog;
//    }
//
//    public static void showConfirmDeclineAlert(
//            final Activity activity,
//            final String msg,
//            final String acceptButtonText,
//            final String declineButtonText,
//            final View.OnClickListener positiveOnClickListener,
//            final View.OnClickListener negativeOnClickListener) {
//        showConfirmDeclineAlert(activity, msg, acceptButtonText, declineButtonText, positiveOnClickListener, negativeOnClickListener, false);
//    }
//
//    /**
//     * Calls {@code show()} getConfirmDeclineGeneralDialog
//     */
//    public static void showConfirmDeclineAlert(
//            final Activity activity,
//            final String msg,
//            final String acceptButtonText,
//            final String declineButtonText,
//            final View.OnClickListener positiveOnClickListener,
//            final View.OnClickListener negativeOnClickListener, boolean verticalButtons) {
//        final Dialog dialog = getConfirmDeclineGeneralDialog(activity, msg, positiveOnClickListener, negativeOnClickListener);
//
//        Button accept = (Button) dialog.findViewById(!verticalButtons ? R.id.buttonAccept : R.id.buttonAcceptVertical);
//        accept.setText(acceptButtonText);
//
//        Button decline = (Button) dialog.findViewById(!verticalButtons ? R.id.buttonDecline : R.id.buttonDeclineVertical);
//        decline.setText(declineButtonText);
//
//        if (verticalButtons) {
//            dialog.findViewById(R.id.horizontalButtons).setVisibility(View.GONE);
//            dialog.findViewById(R.id.verticalButtons).setVisibility(View.VISIBLE);
//
//            accept.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                    if (positiveOnClickListener != null) {
//                        positiveOnClickListener.onClick(v);
//                    }
//                }
//            });
//
//            decline.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                    if (negativeOnClickListener != null) {
//                        negativeOnClickListener.onClick(v);
//                    }
//                }
//            });
//        }
//
//        dialog.show();
//    }
//
//    /**
//     * Calls {@code show()} getConfirmDeclineGeneralDialog
//     */
//    public static void showConfirmDeclineAlert(
//            final Activity activity,
//            final String msg,
//            final View.OnClickListener positiveOnClickListener,
//            final View.OnClickListener negativeOnClickListener) {
//
//        getConfirmDeclineGeneralDialog(activity, msg, positiveOnClickListener, negativeOnClickListener).show();
//    }
//
//    /**
//     * Calls {@code show()} getConfirmDeclineGeneralDialog
//     */
//    public static void showSelectDialog(
//            final Activity activity,
//            final String[] items,
//            AdapterView.OnItemClickListener clickListener) {
//
//        getSelectDialog(activity, items, clickListener).show();
//    }
//
//    /**
//     * Calls {@code show()} on {@link #getMessageDialog(android.app.Activity, String)}
//     */
//    public static void showMessageAlert(final Activity activity, final String msg) {
//        getMessageDialog(activity, msg).show();
//    }
//
//    public static void showMessageAlertWithCallBack(Activity activity, String msg, View.OnClickListener callback) {
//
//        final Dialog dialog = new Dialog(activity);
//        dialog.setContentView(R.layout.dialog_alert);
//        setDialogWidth(activity, dialog);
//
//        TextView dialogTitle = (TextView) dialog.findViewById(R.id.text);
//        dialogTitle.setText(msg);
//
//        Button dialogButton = (Button) dialog.findViewById(R.id.buttonAccept);
//        dialogButton.setOnClickListener(callback);
//
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
//    }
//
//    //TODO use checkIfOnline
//    public static void showOfflineActionAlert(Context context) {
//        showMessageAlert((Activity)context, context.getString(R.string.SorryNoInternet));
//    }
//
//    public static void setDialogWidth(Activity activity, Dialog dialog) {
//        int displaySize;
//        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//            displaySize = UIHelpers.getScreenWidth(activity);
//        } else {
//            displaySize = UIHelpers.getScreenHeight(activity);
//        }
//
//        int dialogWidth = (int) (displaySize * dialogWidthRatio);
//        dialog.getWindow().setLayout(dialogWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
//    }
//
//    public static int getDialogWidth(Activity activity) {
//        int displaySize;
//        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//            displaySize = UIHelpers.getScreenWidth(activity);
//        } else {
//            displaySize = UIHelpers.getScreenHeight(activity);
//        }
//
//        return (int) (displaySize * dialogWidthRatio);
//    }
//}
