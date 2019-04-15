
package com.shoe.utils;

public interface Constant {
	
	
	String PLATFORM_PAD = "pad";
	
	String PLATFORM_WEB = "web";
	
	/**
	 * 邮件发送者
	 */
	String EMAIL_FROM = "evervker@163.com";

	/**
	 * 内容
	 */
	String REST_CONTENT = "<br/>为了确保您的帐号安全，该链接仅1小时内访问有效。如果该链接已经失效，请您点击 这里 重新获取修复密码邮件。<br/>如果点击链接不工作，请您选择并复制整个链接，打开浏览器来运行。请勿直接回复该邮件。<br/> 景界微课服务平台";

	/**
	 * 密钥 -flash-player
	 */
	String SECRET_KEY = "vision-cd1111111";

	/**
	 * 系统默认密码
	 */
	String DEFAULT_PASSWORD = "111111";

	/**
	 * 密码 私钥 vision_ylssygp
	 */
	String SECRET_KEY_PASSWORD = "vision_ylssygp";
	/**
	 * 存放验证码到session 中的KEY 值
	 */
	String RANDOM_CODE_KEY = "random_code_key";
	/**
	 * 验证码输入错误记录cookie 
	 */
	String RANDOM_ERROR_CODE_KEY = "random_error_code_key";
	

	String BIG_PICTURE_WIDTH = "640";
	String BIG_PICTURE_HEIGHT = "360";
	String BIG_PICTURE_SUFFIX = "b";

	String MEDIUM_PICTURE_WIDTH = "240";
	String MEDIUM_PICTURE_HEIGHT = "130";
	String MEDIUM_PICTURE_SUFFIX = "m";

	String SMALL_PICTURE_WIDTH = "80";
	String SMALL_PICTURE_HEIGHT = "45";
	String SMALL_PICTURE_SUFFIX = "s";
	
	
	/**
	 * 平台图片（栏目封面、应用课程封面、应用课程亮点展示、应用课程习题封面、富文本图片、
	 * 名校图片、教学课程封面、教学课程目录图片、教师微课封面、模版封面、模版目录图片、
	 * 教研主题封面）
	 */
	String SHOE_PLATFORM_PICTURE = "/shoe/platform/picture/";
	
	/**
	 * 平台附件（富文本附件、教研主题附件）
	 */
	String SHOE_PLATFORM_ATTACHMENT = "/shoe/platform/attachment/";
	
	/**
	 * 平台微课文件（教师微课文件、解析微课文件）
	 */
	String SHOE_PLATFORM_VKE_FILE = "/shoe/platform/vke_file/";
	
	/**
	 * 平台音视频文件
	 */
	String SHOE_PLATFORM_MEDIA_FILE="/shoe/platform/media_file/";
	/**
	 * 平台习题（习题封面图片、题干封面图片、题干内容图片、题干答案图片、题干解析内容图片）
	 */
	String SHOE_PLATFORM_PRAXIS = "/shoe/platform/praxis/";	
	
	/**
	 * 习题音频文件
	 */
	String SHOE_PLATFORM_PRAXIS_AUDIO = "/shoe/platform/praxis_audio/";	
	
	/**
	 * 课程套题文档（套题文档封面图片、套题文档文件）
	 */
	String SHOE_PLATFORM_COURSE_DOC = "/shoe/platform/course_doc/";	
	
	/**
	 * 课程素材文档
	 */
	String SHOE_PLATFORM_MATERIAL_DOC = "/shoe/platform/material_doc/";	
	
	/**
	 * 平台码表数据静态文件
	 */
	String SHOE_PLATFORM_MAPTREE = "/shoe/platform/maptree/";	
	
	/**
	 * 用户图片（用户头像、后台组合工具用户头像、微课封面、解析微课封面）
	 */
	String SHOE_USER_PICTURE = "/shoe/user/picture/";
	
	/**
	 * 用户贺卡图片
	 */
	String SHOE_USER_GREET_CARD = "/shoe/user/greet_card/";
	
	/**
	 * 用户附件（用户教师微课空间上传录音，图片等）
	 */
	String SHOE_USER_ATTACHMENT = "/shoe/user/attachment/";	
	
	/**
	 * 临时文件（头像保存临时图片，上传微课ZIP ）
	 */
	String SHOE_TEMP = "/shoe/tmp/";
	
	/**
	 * 主题（背景图片、图标、过程图片、音频）
	 */
	String SHOE_TOPIC = "/shoe/themes/";
	
	/**
	 * 习题PDF
	 */
	String SHOE_PRAXIS_PDF = "/shoe/praxis_pdf/";
	
	
	String COOKIE_CURRENT_STU_ID = "current_stu_id";
	String COOKIE_YOUXIN_USER_TOKEN = "youxin_user_token";
	String COOKIE_STUDENT_IDS = "cookie_student_ids";
}
