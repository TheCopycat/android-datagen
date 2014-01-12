package ${package}.data.provider;

import java.util.List;

import android.content.Context;

public interface BeanAccessor<ParentBean> 
{
	public Context getContext();

	public void getDatas();

	public void putResult(List<ParentBean> datas);
}
