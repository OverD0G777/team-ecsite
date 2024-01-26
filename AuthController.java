package jp.co.internous.colors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import jp.co.internous.colors.model.domain.MstUser;
import jp.co.internous.colors.model.form.UserForm;
import jp.co.internous.colors.model.mapper.MstUserMapper;
import jp.co.internous.colors.model.mapper.TblCartMapper;
import jp.co.internous.colors.model.session.LoginSession;

/**
 * 認証に関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@RestController
@RequestMapping("/colors/auth")
public class AuthController {

	/*
	 * フィールド定義
	 */

	@Autowired
	private LoginSession loginsession;

	@Autowired
	MstUserMapper userMapper;

	@Autowired
	TblCartMapper cartMapper;

	private Gson gson = new Gson();

	/**
	 * ログイン処理をおこなう
	 * @param f ユーザーフォーム
	 * @return ログインしたユーザー情報(JSON形式)
	 */
	@PostMapping("/login")
	public String login(@RequestBody UserForm f) {

		MstUser user = userMapper.findByUserNameAndPassword(f.getUserName(), f.getPassword());

		int tmpUserId = loginsession.getTmpUserId();

		if (user != null && tmpUserId != 0) {
			int count = cartMapper.findCountByUserId(tmpUserId);
			if (count > 0) {
				cartMapper.updateUserId(user.getId(), tmpUserId);
			}
		}

		if (user != null) {
			loginsession.setTmpUserId(0);
			loginsession.setLogined(true);
			loginsession.setUserName(user.getUserName());
			loginsession.setPassword(user.getPassword());
			loginsession.setUserId(user.getId());
		} else {
			loginsession.setLogined(false);
			loginsession.setUserId(0);
			loginsession.setUserName(null);
			loginsession.setPassword(null);
		}

		return gson.toJson(user);

	}

	/**
	 * ログアウト処理をおこなう
	 * @return 空文字
	 */
	@PostMapping("/logout")
	public String logout() {

		loginsession.setTmpUserId(0);
		loginsession.setLogined(false);
		loginsession.setUserId(0);
		loginsession.setUserName(null);
		loginsession.setPassword(null);

		return "";
	}

	/**
	 * パスワード再設定をおこなう
	 * @param f ユーザーフォーム
	 * @return 処理後のメッセージ
	 */
	@PostMapping("/resetPassword")
	public String resetPassword(@RequestBody UserForm f) {

		String newPassword = f.getNewPassword();

		MstUser user = userMapper.findByUserNameAndPassword(f.getUserName(), f.getPassword());

		if (user.getPassword().equals(newPassword)) {
			return "現在のパスワードと同一文字列が入力されました。";
		}

		userMapper.updatePassword(user.getUserName(), f.getNewPassword());
		loginsession.setPassword(f.getNewPassword());

		return "パスワードが再設定されました。";
	}
}
