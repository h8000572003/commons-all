package io.github.h800572003.telegram;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * bot 資料存取
 * 
 * @author 6407
 *
 */
public interface IBotRepository {

	/**
	 * 取得所有用戶
	 * 
	 * @return
	 */
	List<String> getUser();

	/**
	 * 取得相關資訊
	 * 
	 * @return
	 */
	BotMedidata getBotMedidata();

	/**
	 * 是否腳色有權限
	 * 
	 * @param userId
	 * @return
	 */
	boolean isAuthorize(String userId);

	void update(BotSystem botSystem);

	List<BotSystem> find(BotType botType, String value1);

	/**
	 * null資料不當參數查詢
	 * 
	 * @param supplier
	 * @return
	 */
	List<BotSystem> selectOption(Consumer<BotSystem> supplier);

}
