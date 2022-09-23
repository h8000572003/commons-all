package io.github.h800572003.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.util.CollectionUtils;

import io.github.h800572003.zip.exception.ZipBusinessException;

public class ZipService {

	public List<File> makeZip(ZipMakeOption zipMakeOption) {//
		zipMakeOption.check();
		final List<File> retunrZip = new ArrayList<>();
		final List<File> waitFiles = new ArrayList<>();
		for (File file : zipMakeOption.getFiles()) {
			waitFiles.add(file);
			if (waitFiles.size() >= zipMakeOption.getLimit()) {
				retunrZip.add(this.zipFile(waitFiles, zipMakeOption));
			}
		}
		if (!CollectionUtils.isEmpty(waitFiles)) {
			retunrZip.add(this.zipFile(waitFiles, zipMakeOption));
		}
		return retunrZip;
	}

	private File zipFile(List<File> waitFiles, ZipMakeOption zipMakeOption) {
		String zipFold = zipMakeOption.getZipFold();
		File zipFile = getFileName(zipMakeOption, zipFold);
		try (FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
				ZipOutputStream zipOut = new ZipOutputStream(fileOutputStream);) {
			for (File file : waitFiles) {
				ZipEntry zipEntry = new ZipEntry(file.getName());
				zipOut.putNextEntry(zipEntry);
				byte[] bytes = new byte[1024];
				int length;
				try (FileInputStream fis = new FileInputStream(file)) {
					while ((length = fis.read(bytes)) >= 0) {
						zipOut.write(bytes, 0, length);
					}
				} catch (IOException e) {
					throw new ZipBusinessException("file:{0} IOException", file.getName(), e);
				}
			}
			zipMakeOption.getMakeZipListener().update(waitFiles);
			return zipFile;
		} catch (IOException e) {
			throw new ZipBusinessException("IOException", e);
		} finally {
			waitFiles.clear();
		}
	}

	private File getFileName(ZipMakeOption zipMakeOption, String zipFold) {
		File zipFile = null;
		boolean isOk = false;
		for (int i = 0; i < zipMakeOption.getNameCheckSize(); i++) {
			zipFile = new File(zipFold, zipMakeOption.getZipNameStrategy().get());
			if (!zipFile.exists()) {
				isOk = true;
			}
		}
		if (!isOk) {
			throw new ZipBusinessException("name same over try time:{0}", zipMakeOption.getNameCheckSize());
		}
		return zipFile;
	}

}
