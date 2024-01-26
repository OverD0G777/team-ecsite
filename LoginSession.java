package jp.co.internous.colors.model.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * ログインセッション
 * @author インターノウス
 *
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginSession implements Serializable {
	private static final long serialVersionUID = -4505629762363906244L;

	private int userId;

	private int tmpUserId;

	private String userName;

	private String password;

	private boolean logined;

	/*
	 * 以下にGetter / Setter メソッドを作成
	 */

	/**
	 * ユーザーIDを取得する
	 * @return　ユーザーID
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * ユーザーIDを設定する
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 仮ユーザーIDを設定する
	 * @return　ユーザーID
	 */
	public int getTmpUserId() {
		return tmpUserId;
	}

	/**
	 * 仮ユーザーIDを取得する
	 * @param tmpUserId
	 */
	public void setTmpUserId(int tmpUserId) {
		this.tmpUserId = tmpUserId;
	}

	/**
	 * ユーザー名を設定する
	 * @return　ユーザー名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ユーザー名を取得する
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * パスワードを設定する
	 * @return　パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードを取得する
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ログインフラグを取得する
	 * @return　ログインフラグ
	 */
	public boolean getLogined() {
		return logined;
	}

	/**
	 * ログインフラグを設定する
	 * @param logined　ログインフラグ
	 */
	public void setLogined(boolean logined) {
		this.logined = logined;
	}

}
