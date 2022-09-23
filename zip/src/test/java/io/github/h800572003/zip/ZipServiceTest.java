package io.github.h800572003.zip;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Disabled
public class ZipServiceTest {
	ZipService zipService = new ZipService();

	@Test
	void testZip() throws URISyntaxException {

		ClassLoader classLoader = ZipServiceTest.class.getClassLoader();
		URL resource1 = classLoader.getResource("file1.txt");
		URL resource2 = classLoader.getResource("file2.txt");

		List<File> files = Arrays.asList(//
				new File(resource1.toURI()), //
				new File(resource2.toURI()));//

		ZipMakeOption option = new ZipMakeOption(this::getFileName, 1, "/ZIP");
		option.addAllFile(files);
		option.setMakeZipListener(file -> log.info("ok file size:{}", file.size()));
		List<File> makeZip = zipService.makeZip(option);
		assertThat(makeZip).isNotEmpty();
	}

	private String getFileName() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + ".zip";
	}

	@Test
	void testZipSize() throws URISyntaxException {

		ClassLoader classLoader = ZipServiceTest.class.getClassLoader();

		List<File> files = Arrays.asList(//
				new File(classLoader.getResource("file1.txt").toURI()), //
				new File(classLoader.getResource("file2.txt").toURI()),
				new File(classLoader.getResource("file3.txt").toURI()),
				new File(classLoader.getResource("file4.txt").toURI()),
				new File(classLoader.getResource("file5.txt").toURI()),
				new File(classLoader.getResource("file6.txt").toURI()),
				new File(classLoader.getResource("file7.txt").toURI()),
				new File(classLoader.getResource("file8.txt").toURI()),

				new File(classLoader.getResource("file9.txt").toURI()),
				new File(classLoader.getResource("file10.txt").toURI()),

				new File(classLoader.getResource("file11.txt").toURI()),
				new File(classLoader.getResource("file12.txt").toURI()));//

		ZipMakeOption option = new ZipMakeOption(this::getFileName, 5, "/ZIP");
		option.addAllFile(files);
		option.setMakeZipListener(file -> log.info("ok file size:{}", file.size()));
		List<File> makeZip = zipService.makeZip(option);
		assertThat(makeZip).isNotEmpty();
	}

}
