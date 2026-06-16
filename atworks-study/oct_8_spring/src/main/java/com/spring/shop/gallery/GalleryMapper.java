package com.spring.shop.gallery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GalleryMapper  {

	public abstract int insert(GalleryVO gallery);

	public abstract int getTotal();

	public abstract ArrayList<GalleryVO> getList(Map<String, Object> page);

	public abstract GalleryVO view(int gi_no);

	public abstract void modify(GalleryVO gallery);

	public abstract void delete(int gi_no);

}
