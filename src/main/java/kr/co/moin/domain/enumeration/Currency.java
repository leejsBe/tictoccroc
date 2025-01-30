package kr.co.moin.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Currency {

  USD() {
    @Override
    public double fixedFee(double amount) {
      if (amount > 0 && amount <= 1000000) {
        return 1000;
      }

      if (amount > 1000000) {
        return 3000;
      }

      return 0;
    }

    @Override
    public double commissionRate(double amount) {
      if (amount > 0 && amount <= 1000000) {
        return 0.2;
      }

      if (amount > 1000000) {
        return 0.1;
      }

      return 0;
    }
  },

  JPY() {
    @Override
    public double fixedFee(double amount) {
      if (amount > 0) {
        return 3000;
      }

      return 0;
    }

    @Override
    public double commissionRate(double amount) {
      if (amount > 0) {
        return 0.5;
      }

      return 0;
    }
  };


  /// 고정 수수료
  public abstract double fixedFee(double amount);

  /// 수수료율
  public abstract double commissionRate(double amount);


}
