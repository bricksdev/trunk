package cn.com.parese.element;


import cn.com.parese.exception.PareseException;

import org.dom4j.Element;


public interface BaseElement {


	void doParese(Element element) throws PareseException;
	
	BaseElement NULL = new BaseElement(){
		public void doParese(Element element){
			throw new RuntimeException("Not Support Exception.");
		}
	};
}
