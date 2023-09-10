package com.example.newsapitest.model.source;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SourceResponse{

	@SerializedName("sources")
	private List<SourcesItem> sources;

	@SerializedName("status")
	private String status;

	public List<SourcesItem> getSources(){
		return sources;
	}

	public String getStatus(){
		return status;
	}
}