package com.amytech.android.library.utils.download;

public class DownloadInfo {

	// 下载器id
	private int threadId;
	// 开始点
	private int startPos;
	// 结束点
	private int endPos;
	// 完成度
	private int compeleteSize;
	// 下载文件的URL地址
	private String url;

	public DownloadInfo(int threadId, int startPos, int endPos, int compeleteSize, String url) {
		this.threadId = threadId;
		this.startPos = startPos;
		this.endPos = endPos;
		this.compeleteSize = compeleteSize;
		this.url = url;
	}

	public DownloadInfo() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getEndPos() {
		return endPos;
	}

	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}

	public int getCompeleteSize() {
		return compeleteSize;
	}

	public void setCompeleteSize(int compeleteSize) {
		this.compeleteSize = compeleteSize;
	}

	@Override
	public String toString() {
		return "DownloadInfo [threadId=" + threadId + ", startPos=" + startPos + ", endPos=" + endPos + ", compeleteSize=" + compeleteSize + "]";
	}
}
