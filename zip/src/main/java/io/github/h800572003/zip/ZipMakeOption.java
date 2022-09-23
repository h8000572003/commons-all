package io.github.h800572003.zip;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import io.github.h800572003.zip.exception.ZipBusinessException;

public class ZipMakeOption {
	private final Supplier<String> zipNameStrategy;
	private int limit = -1;
	private final List<File> files = new ArrayList<>();
	private String zipFold;
	private int nameCheckSize = 3;
	private MakeZipListener makeZipListener = new MakeZipListener() {

		@Override
		public void update(List<File> file) {
		}
	};

	public MakeZipListener getMakeZipListener() {
		return makeZipListener;
	}

	public void setMakeZipListener(MakeZipListener makeZipListener) {
		this.makeZipListener = makeZipListener;
	}

	public interface MakeZipListener {
		void update(List<File> file);
	}

	public ZipMakeOption(Supplier<String> zipNameStrategy, int limit, String zipFold) {
		super();
		this.zipNameStrategy = zipNameStrategy;
		this.limit = limit;
		this.zipFold = zipFold;
	}

	public void setZipFold(String zipFold) {
		this.zipFold = zipFold;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void addFile(File file) {
		this.files.add(file);
	}

	public void addAllFile(List<File> files) {
		this.files.addAll(files);
	}

	public Supplier<String> getZipNameStrategy() {
		return this.zipNameStrategy;
	}

	public int getLimit() {
		return this.limit;
	}

	public List<File> getFiles() {
		return this.files;
	}

	public String getZipFold() {
		return this.zipFold;
	}

	public int getNameCheckSize() {
		return nameCheckSize;
	}

	public void setNameCheckSize(int nameCheckSize) {
		this.nameCheckSize = nameCheckSize;
	}

	public void check() {
		if (this.limit <= 0) {
			throw new ZipBusinessException("limit size >0");
		}
		if (this.zipNameStrategy == null) {
			throw new ZipBusinessException("name is require ");
		}
		if (CollectionUtils.isEmpty(this.files)) {
			throw new ZipBusinessException("file is empty");
		}
		if (StringUtils.isBlank(this.zipFold)) {
			throw new ZipBusinessException("zipFold is require");
		}
	}
}
