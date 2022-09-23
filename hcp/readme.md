## HCP範例程式

### 注意事項

參數執行HcpServiceTest前須調整參數，輸入正確參數

```
String user = "user";
String pwd = "pwd";
String url = "url";
```
1.建立常用通用方式
 - put上傳檔案
 - get取得檔案
 - delete 刪除檔案
 
 
 ```
 
 //取得檔案範例
 @Test
	void test_get_file_then_HcpGet_then_file() {

		HcpGet hcpDownloadInfo = new HcpGet();
		String restPath = HcpUtil.toRestPath("x.pdf", "1", "2", "3", "4");
		hcpDownloadInfo.setPath(restPath);

		GetHcpInfo execute;
		try {
			execute = hcpService.execute(hcpDownloadInfo);
			String size = execute.getSize();
			assertThat(size).isNotBlank();
		} catch (HcpCodeExcepton e) {
			fail("");
		}

	}
 //取得檔案範例
	@Test
	void test_delet_file_then_HcpGet_then_ok() {
		HcpDelete cmd = new HcpDelete();
		String restPath = HcpUtil.toRestPath("x.pdf", "1", "2", "3", "4");
		cmd.setPath(restPath);

		try {
			BaseHcpInfo execute = hcpService.execute(cmd);
		} catch (HcpCodeExcepton e) {
			e.printStackTrace();
		}

	}

	@Test
	void test_put_file_then_put_then_ok() throws IOException, URISyntaxException {

		String restPath = HcpUtil.toRestPath("x.pdf", "1", "2", "3", "4");
		URL resource = HcpServiceTest.class.getClassLoader().getResource("test.zip");

		byte[] myByte = FileUtils.readFileToByteArray(new File(resource.toURI()));

		HcpPut cmd = new HcpPut(restPath, myByte);

		try {
			BaseHcpInfo execute = hcpService.execute(cmd);
			log.info("execute:{}", execute);
		} catch (HcpCodeExcepton e) {
			log.info("error code:{}", e.getCode(), e);
		}

	}
 
 ```

