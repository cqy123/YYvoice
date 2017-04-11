package com.beicai.yyvoice.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.beicai.yyvoice.application.MyApplication;


public abstract class BaseActivity extends FragmentActivity {

	protected MyApplication application;
	protected Activity _context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//获取全局的application对象
		application = (MyApplication) this.getApplication();
		//把activity添加并保存到application中
		application.addActivity(this);
		setCustomLayout(savedInstanceState);
		initView();
		bindComponentEvent();
		initData();
	}
	
	/**
	 * 设置布局
	 * @param savedInstanceState
	 */
	protected abstract void setCustomLayout(Bundle savedInstanceState);
	
	/**
	 * 初始化组建
	 */
	protected abstract void initView();
	
	/**
	 * 绑定控件事件
	 */
	protected abstract void bindComponentEvent();
	
	/**
	 * 解析获得数据后的设置等操作
	 */
	protected abstract void initData();

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (resultCode == Activity.RESULT_OK) {
			doActivityResult(requestCode, intent);
		}
	}
	
	protected abstract void doActivityResult(int requestCode, Intent intent);
	
}
