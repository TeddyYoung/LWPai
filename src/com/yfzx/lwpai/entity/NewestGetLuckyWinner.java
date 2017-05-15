package com.yfzx.lwpai.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 最新揭晓 往期揭晓界面
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月13日
 */
public class NewestGetLuckyWinner implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3826024044990430286L;
	/**
	 * message : 成功获取往期中拍记录 result :
	 * [{"LuckyIp":"27.150.135.33","SumCount":"400"
	 * ,"winLotteryNum":"2,9,10,24,30,1,8"
	 * ,"EndDate":"2015/5/21 21:50:00","LuckyCity"
	 * :"厦门市","PeriodIndex":"3","ProductName"
	 * :"NEBU Living Gold Eye Romance 霓璞活金媚眼霜 15ml"
	 * ,"UserName":"w3**10","FaceImage"
	 * :"http://www.lwpai.com/utility/pics/tx.gif","LuckyProvince":"福建省"}]
	 * success : True Total : 3
	 */
	private String message;
	private List<ResultEntity> result;
	private List<PImgSrcEntity> PImgSrc;
	private String success;
	private int Total;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<ResultEntity> result) {
		this.result = result;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public void setTotal(int Total) {
		this.Total = Total;
	}

	public String getMessage() {
		return message;
	}

	public List<ResultEntity> getResult() {
		return result;
	}

	public String getSuccess() {
		return success;
	}

	public int getTotal() {
		return Total;
	}

	public class PImgSrcEntity {
		/**
		 * ImgSrc : http://img.lwpai.com/Storage/master/product/images/4
		 * a546ad8034b489d95abcd2a42bb6750.jpg
		 */
		private String ImgSrc;

		public void setImgSrc(String ImgSrc) {
			this.ImgSrc = ImgSrc;
		}

		public String getImgSrc() {
			return ImgSrc;
		}
	}

	public class ResultEntity {
		/**
		 * LuckyIp : 27.150.135.33 SumCount : 400 winLotteryNum :
		 * 2,9,10,24,30,1,8 EndDate : 2015/5/21 21:50:00 LuckyCity : 厦门市
		 * PeriodIndex : 3 ProductName : NEBU Living Gold Eye Romance 霓璞活金媚眼霜
		 * 15ml UserName : w3**10 FaceImage :
		 * http://www.lwpai.com/utility/pics/tx.gif LuckyProvince : 福建省
		 */
		private String LuckyIp;
		private String SumCount;
		private String winLotteryNum;
		private String EndDate;
		private String LuckyCity;
		private String PeriodIndex;
		private String ProductName;
		private String UserName;
		private String FaceImage;
		private String LuckyProvince;
		private int MarketPrice;
		private String LotteryResults;
		private String WinnerName;
		private String CurrentCount;
		private String WinUserId;
		private String OfferNum;
		private String OfferTime;
		private String OnePurchaseTime;
		private String OnePurchaseCount;
		private String LuckyId;
		private String TryAreaProductId;
		private String OnePurchasePId;

		public String getLuckyId() {
			return LuckyId;
		}

		public void setLuckyId(String luckyId) {
			LuckyId = luckyId;
		}

		public String getTryAreaProductId() {
			return TryAreaProductId;
		}

		public void setTryAreaProductId(String tryAreaProductId) {
			TryAreaProductId = tryAreaProductId;
		}

		public String getOnePurchasePId() {
			return OnePurchasePId;
		}

		public void setOnePurchasePId(String onePurchasePId) {
			OnePurchasePId = onePurchasePId;
		}

		public String getOnePurchaseTime() {
			return OnePurchaseTime;
		}

		public void setOnePurchaseTime(String onePurchaseTime) {
			OnePurchaseTime = onePurchaseTime;
		}

		public String getOnePurchaseCount() {
			return OnePurchaseCount;
		}

		public void setOnePurchaseCount(String onePurchaseCount) {
			OnePurchaseCount = onePurchaseCount;
		}

		public String getOfferTime() {
			return OfferTime;
		}

		public void setOfferTime(String offerTime) {
			OfferTime = offerTime;
		}

		public String getOfferNum() {
			return OfferNum;
		}

		public void setOfferNum(String offerNum) {
			OfferNum = offerNum;
		}

		public String getOnePurchasePTId() {
			return OnePurchasePTId;
		}

		public void setOnePurchasePTId(String onePurchasePTId) {
			OnePurchasePTId = onePurchasePTId;
		}

		private String UnitPrice;
		private int ProductId;
		private String OnePurchasePTId;
		private String Fees;
		private String LimitCount;
		private int SalePrice;
		private String StartDate;
		private String WinnerNum;

		public int getMarketPrice() {
			return MarketPrice;
		}

		public void setMarketPrice(int marketPrice) {
			MarketPrice = marketPrice;
		}

		public String getLotteryResults() {
			return LotteryResults;
		}

		public void setLotteryResults(String lotteryResults) {
			LotteryResults = lotteryResults;
		}

		public String getWinnerName() {
			return WinnerName;
		}

		public void setWinnerName(String winnerName) {
			WinnerName = winnerName;
		}

		public String getCurrentCount() {
			return CurrentCount;
		}

		public void setCurrentCount(String currentCount) {
			CurrentCount = currentCount;
		}

		public String getWinUserId() {
			return WinUserId;
		}

		public void setWinUserId(String winUserId) {
			WinUserId = winUserId;
		}

		public String getUnitPrice() {
			return UnitPrice;
		}

		public void setUnitPrice(String unitPrice) {
			UnitPrice = unitPrice;
		}

		public int getProductId() {
			return ProductId;
		}

		public void setProductId(int productId) {
			ProductId = productId;
		}

		public String getFees() {
			return Fees;
		}

		public void setFees(String fees) {
			Fees = fees;
		}

		public String getLimitCount() {
			return LimitCount;
		}

		public void setLimitCount(String limitCount) {
			LimitCount = limitCount;
		}

		public int getSalePrice() {
			return SalePrice;
		}

		public void setSalePrice(int salePrice) {
			SalePrice = salePrice;
		}

		public String getStartDate() {
			return StartDate;
		}

		public void setStartDate(String startDate) {
			StartDate = startDate;
		}

		public String getWinnerNum() {
			return WinnerNum;
		}

		public void setWinnerNum(String winnerNum) {
			WinnerNum = winnerNum;
		}

		public String getId() {
			return Id;
		}

		public void setId(String id) {
			Id = id;
		}

		public String getMaxNumPercentage() {
			return MaxNumPercentage;
		}

		public void setMaxNumPercentage(String maxNumPercentage) {
			MaxNumPercentage = maxNumPercentage;
		}

		public String getOrderShareId() {
			return OrderShareId;
		}

		public void setOrderShareId(String orderShareId) {
			OrderShareId = orderShareId;
		}

		public String getIsFinish() {
			return IsFinish;
		}

		public void setIsFinish(String isFinish) {
			IsFinish = isFinish;
		}

		private String Id;
		private String MaxNumPercentage;
		private String OrderShareId;
		private String IsFinish;

		public void setLuckyIp(String LuckyIp) {
			this.LuckyIp = LuckyIp;
		}

		public void setSumCount(String SumCount) {
			this.SumCount = SumCount;
		}

		public void setWinLotteryNum(String winLotteryNum) {
			this.winLotteryNum = winLotteryNum;
		}

		public void setEndDate(String EndDate) {
			this.EndDate = EndDate;
		}

		public void setLuckyCity(String LuckyCity) {
			this.LuckyCity = LuckyCity;
		}

		public void setPeriodIndex(String PeriodIndex) {
			this.PeriodIndex = PeriodIndex;
		}

		public void setProductName(String ProductName) {
			this.ProductName = ProductName;
		}

		public void setUserName(String UserName) {
			this.UserName = UserName;
		}

		public void setFaceImage(String FaceImage) {
			this.FaceImage = FaceImage;
		}

		public void setLuckyProvince(String LuckyProvince) {
			this.LuckyProvince = LuckyProvince;
		}

		public String getLuckyIp() {
			return LuckyIp;
		}

		public String getSumCount() {
			return SumCount;
		}

		public String getWinLotteryNum() {
			return winLotteryNum;
		}

		public String getEndDate() {
			return EndDate;
		}

		public String getLuckyCity() {
			return LuckyCity;
		}

		public String getPeriodIndex() {
			return PeriodIndex;
		}

		public String getProductName() {
			return ProductName;
		}

		public String getUserName() {
			return UserName;
		}

		public String getFaceImage() {
			return FaceImage;
		}

		public String getLuckyProvince() {
			return LuckyProvince;
		}
	}
}
