package com.gh.boot.common.core.data;


import lombok.Data;

import java.io.InputStream;
import java.io.Serializable;

@Data
public class AppResource implements Serializable {
	private static final long serialVersionUID = 1L;
	private InputStream inputStream;
	private String parentPath;
	private String path;
	private Object resource;
}
