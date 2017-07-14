package com.boke.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.boke.pojo.Menu;

public class MenuUtil {
	public static Map<Menu, List<Menu>> getMenu(List<Menu> list){

		//去除重复菜单
		for ( int i = 0 ; i < list.size() - 1 ; i++ ) {
			for ( int j = list.size() - 1 ; j > i; j-- ) {
				if ((list.get(j).getDescription()).equals( list.get(i).getDescription())) {
					list.remove(j);
				}
			}
		}
		Map<Menu, List<Menu>> map = new TreeMap<Menu, List<Menu>>(); 
		Iterator<Menu> it = list.iterator();
		while(it.hasNext()){
			Menu menu = it.next();
			if(menu.getChildren()!=null)
				continue;
			List<Menu> sons = new ArrayList<Menu>();
			for(Menu son:list){
				if(son.getChildren()==menu.getId()){
					sons.add(son);
				}
			}
			map.put(menu, sons);
			it.remove();
		}
		return map;
	}
}
