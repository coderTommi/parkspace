package test.rules;

/**
 * 自动刷新缓存类.
 * 
 * @author lidongliang
 *
 */
public abstract class AutoFreshCachedData extends CachedData {
	/** 更新周期 **/
	private long period = 86400000L;
	/**
	 * 判断缓存数据是否失效/超时.
	 * 
	 * @return
	 */
	public boolean isDirty() {
		//根據週期判斷是否失效
		return false;
	}
	/**
	 * 更新数据.
	 */
	protected final void updateCache() {
		updateCacheInner();
	}
	protected abstract void updateCacheInner();
	public long getPeriod() {
		return period;
	}
	public void setPeriod(long period) {
		this.period = period;
	}
}
