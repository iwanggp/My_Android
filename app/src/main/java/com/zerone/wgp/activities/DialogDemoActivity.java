package com.zerone.wgp.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DialogDemoActivity extends AppCompatActivity implements View.OnClickListener {
	private Button bt_custome, bt_list, bt_dx, bt_dxdx, bt_pt;
	private static final String TAG = "DialogDemoActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_demo);
		bt_custome = (Button) findViewById(R.id.bt_custom);
		bt_custome.setOnClickListener(this);
		bt_pt = (Button) findViewById(R.id.bt_pt);
		bt_pt.setOnClickListener(this);
		bt_list = (Button) findViewById(R.id.bt_list);
		bt_list.setOnClickListener(this);
		bt_dx = (Button) findViewById(R.id.bt_dx);
		bt_dx.setOnClickListener(this);
		bt_dxdx = (Button) findViewById(R.id.bt_dxdx);
		bt_dxdx.setOnClickListener(this);

	}

	private void dialog1_1() {
		//先new出一个监听器，设置好监听
		DialogInterface.OnClickListener dialogOnclicListener = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
					case Dialog.BUTTON_POSITIVE:
						Toast.makeText(DialogDemoActivity.this, "确认" + which, Toast.LENGTH_SHORT).show();
						break;
					case Dialog.BUTTON_NEGATIVE:
						Toast.makeText(DialogDemoActivity.this, "取消" + which, Toast.LENGTH_SHORT).show();
						break;
					case Dialog.BUTTON_NEUTRAL:
						Toast.makeText(DialogDemoActivity.this, "忽略" + which, Toast.LENGTH_SHORT).show();
						break;
				}
			}
		};
		//dialog参数设置
		AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
		builder.setTitle("提示对话框"); //设置标题
		builder.setMessage("是否确认退出?"); //设置内容
		builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
		builder.setPositiveButton("确认", dialogOnclicListener);
		builder.setNegativeButton("取消", dialogOnclicListener);
		builder.setNeutralButton("忽略", dialogOnclicListener);
		builder.create().show();
	}

	private void dialog2() {
		final String items[] = {"软件部", "BSP部", "测试部"};
		//dialog参数设置
		AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
		builder.setTitle("列表对话框"); //设置标题
		builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
		//设置列表显示，注意设置了列表显示就不要设置builder.setMessage()了，否则列表不起作用。
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Toast.makeText(DialogDemoActivity.this, items[which], Toast.LENGTH_SHORT).show();
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Toast.makeText(DialogDemoActivity.this, "确定", Toast.LENGTH_SHORT).show();
			}
		});
		builder.create().show();
	}

	private void dialog3() {
		final String items[] = {"男", "女"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
		builder.setTitle("单选对话框"); //设置标题
		builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
		builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//dialog.dismiss();
				Toast.makeText(DialogDemoActivity.this, items[which], Toast.LENGTH_SHORT).show();
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Toast.makeText(DialogDemoActivity.this, "确定", Toast.LENGTH_SHORT).show();
			}
		});
		builder.create().show();
	}

	private void dialog4() {
		final String items[] = {"篮球", "足球", "排球"};
		final boolean selected[] = {true, false, true};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
		builder.setTitle("多选列表对话框"); //设置标题
		builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
		builder.setMultiChoiceItems(items, selected, new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// dialog.dismiss();
				Toast.makeText(DialogDemoActivity.this, items[which] + isChecked, Toast.LENGTH_SHORT).show();
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Toast.makeText(DialogDemoActivity.this, "确定", Toast.LENGTH_SHORT).show();
				//android会自动根据你选择的改变selected数组的值。
				for (int i = 0; i < selected.length; i++) {
					Log.e("hongliang", "" + selected[i]);
				}
			}
		});
		builder.create().show();
	}

	private void dialog5() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
		builder.setTitle("输入对话框"); //设置标题
		builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
		builder.setView(R.layout.dialog_bottom);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Toast.makeText(DialogDemoActivity.this, "确定", Toast.LENGTH_SHORT).show();
			}
		});
		builder.create().show();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.bt_custom:
				Log.d(TAG, "onClick: ......run");
				dialog1_1();
				break;
			case R.id.bt_pt:
				dialog2();
				break;
			case R.id.bt_list:
				dialog3();
				break;
			case R.id.bt_dx:
				dialog4();
				break;
			case R.id.bt_dxdx:
				dialog5();
				break;

		}
	}
}
