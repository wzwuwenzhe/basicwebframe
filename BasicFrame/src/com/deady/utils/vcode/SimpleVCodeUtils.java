package com.deady.utils.vcode;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.CookieStore;

public class SimpleVCodeUtils {

	private static HttpClient httpClient = new HttpClient();
	private CookieStore cookieStore = null;

	public SimpleVCodeUtils(CookieStore cookieStore) {
		if (httpClient == null) {
			httpClient = new HttpClient();
		}
		this.cookieStore = cookieStore;
	}

	public static int isWhite(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() > 100) {
			return 1;
		}
		return 0;
	}

	public static int isBlack(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() <= 100) {
			return 1;
		}
		return 0;
	}

	public static BufferedImage removeBackgroud(String picFile)
			throws Exception {
		BufferedImage img = ImageIO.read(new File(picFile));
		int width = img.getWidth();
		int height = img.getHeight();
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				if (isWhite(img.getRGB(x, y)) == 1) {
					img.setRGB(x, y, Color.WHITE.getRGB());
				} else {
					img.setRGB(x, y, Color.BLACK.getRGB());
				}
			}
		}
		return img;
	}

	public static List<BufferedImage> splitImage(BufferedImage img)
			throws Exception {
		List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
		subImgs.add(img.getSubimage(3, 4, 10, 13));
		subImgs.add(img.getSubimage(17, 2, 10, 13));
		subImgs.add(img.getSubimage(32, 5, 10, 13));
		subImgs.add(img.getSubimage(47, 3, 10, 13));
		return subImgs;
	}

	public static Map<BufferedImage, String> loadTrainData(String filePath)
			throws Exception {
		Map<BufferedImage, String> map = new HashMap<BufferedImage, String>();
		File dir = new File(filePath);
		File[] files = dir.listFiles();
		for (File file : files) {
			map.put(ImageIO.read(file), file.getName().charAt(0) + "");
		}
		return map;
	}

	public static String getSingleCharOcr(BufferedImage img,
			Map<BufferedImage, String> map) {
		String result = "";
		int width = img.getWidth();
		int height = img.getHeight();
		int min = width * height;
		for (BufferedImage bi : map.keySet()) {
			int count = 0;
			Label1: for (int x = 0; x < width; ++x) {
				for (int y = 0; y < height; ++y) {
					if (isWhite(img.getRGB(x, y)) != isWhite(bi.getRGB(x, y))) {
						count++;
						if (count >= min)
							break Label1;
					}
				}
			}
			if (count < min) {
				min = count;
				result = map.get(bi);
			}
		}
		return result;
	}

	public static String getAllOcr(String file, String filePath)
			throws Exception {
		BufferedImage img = removeBackgroud(file);
		List<BufferedImage> listImg = splitImage(img);
		Map<BufferedImage, String> map = loadTrainData(filePath);
		String result = "";
		for (BufferedImage bi : listImg) {
			result += getSingleCharOcr(bi, map);
		}
		ImageIO.write(img, "JPG", new File(
				"C:\\Users\\wzwuw\\git\\basicframe\\BasicFrame\\img\\result\\"
						+ result + ".jpg"));
		return result;
	}

	public static void downloadImage(String uri, String picName) {
		GetMethod getMethod = new GetMethod(uri);
		// for (int i = 0; i < 30; i++) {
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			// 读取内容
			// String picName =
			// "C:\\Users\\wzwuw\\git\\basicframe\\BasicFrame\\img\\vcode\\"
			// + fileName + ".jpg";
			InputStream inputStream = getMethod.getResponseBodyAsStream();
			OutputStream outStream = new FileOutputStream(picName);
			IOUtils.copy(inputStream, outStream);
			outStream.close();
			System.out.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		// }
	}

	/**
	 * 
	 * @param url
	 *            获取验证码的地址
	 * @param filePath
	 *            文件路径
	 * @param fileName
	 *            文件名称
	 * @return
	 * @throws Exception
	 */
	public static String getVCodeByUrl(String url, String filePath,
			String fileName) throws Exception {
		String result = "";
		SimpleVCodeUtils.downloadImage(url, filePath + "\\vcode\\" + fileName
				+ ".jpg");
		BufferedImage img = removeBackgroud(filePath + "\\vcode\\" + fileName
				+ ".jpg");
		List<BufferedImage> listImg = splitImage(img);
		Map<BufferedImage, String> map = loadTrainData(filePath + "train");
		for (BufferedImage bi : listImg) {
			result += getSingleCharOcr(bi, map);
		}
		ImageIO.write(img, "JPG", new File(filePath + "result\\" + result
				+ ".jpg"));
		// 获取sessionId

		return result;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 30; ++i) {
			getAllOcr("C:\\Users\\wzwuw\\git\\basicframe\\BasicFrame\\img\\"
					+ i + ".jpg",
					"C:\\Users\\wzwuw\\git\\basicframe\\BasicFrame\\img\\train");
		}

	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	/**
	 * 根据访问地址 获取到返回的页面 再获取到token
	 * 
	 * @param loginUrl
	 * @return
	 */
	public String getTokenByUrl(String loginUrl) {

		return null;
	}

}
