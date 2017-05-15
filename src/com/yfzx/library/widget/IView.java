package com.yfzx.library.widget;

import com.yfzx.lwpai.MApplication;

/**
 * 自定义View需要用到的常量、方法
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-4
 */
public interface IView {

	/** 应用的包名称 **/
	String PACKAGE_NAME = MApplication.getContext().getPackageName();

	/*** 资源类型-array **/
	String ARRAY = "array";

	/*** 资源类型-attr **/
	String ATTR = "attr";

	/*** 资源类型-bool **/
	String BOOL = "bool";

	/*** 资源类型-color **/
	String COLOR = "color";

	/*** 资源类型-dimen **/
	String DIMEN = "dimen";

	/*** 资源类型-drawable **/
	String DRAWABLE = "drawable";

	/*** 资源类型-id **/
	String ID = "id";

	/*** 资源类型-id **/
	String INTEGER = "integer";

	/*** 资源类型-layout **/
	String LAYOUT = "layout";

	/*** 资源类型-drawable **/
	String STRING = "string";

	/*** 资源类型-style **/
	String STYLE = "style";

	/*** 资源类型-styleable **/
	String STYLEABLE = "styleable";

}
