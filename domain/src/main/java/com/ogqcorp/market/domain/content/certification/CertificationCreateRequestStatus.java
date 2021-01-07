package com.ogqcorp.market.domain.content.certification;

public enum CertificationCreateRequestStatus {

  // INIT -> STARTED or REJECTED -> REQUESTED -> WAITED -> COMPLETED or FAILED

  // 요청이 생성됨
  INIT {
    @Override
    public boolean canChangeableTo(CertificationCreateRequestStatus status) {
      return status == STARTED || status == REJECTED;
    }
  },

  // 프로세스(해시 생성 또는 추가) 시작됨
  STARTED {
    @Override
    public boolean canChangeableTo(CertificationCreateRequestStatus status) {
      return status == REQUESTED || status == FAILED;
    }
  },

  // 요청 작업이 거부됨
  REJECTED {
    @Override
    public boolean canChangeableTo(CertificationCreateRequestStatus status) {
      return status == STARTED;
    }
  },

  // (해시 생성 또는 추가)작업을 요청함
  REQUESTED {
    @Override
    public boolean canChangeableTo(CertificationCreateRequestStatus status) {
      return status == WAITED || status == FAILED;
    }
  },

  // (해시 생성 또는 추가)작업이 성공하여 인증서 생성 또는 업데이트 작업을 대기 중
  WAITED {
    @Override
    public boolean canChangeableTo(CertificationCreateRequestStatus status) {
      return status == COMPLETED || status == FAILED;
    }
  },

  // 프로세스 성공함
  COMPLETED {
    @Override
    public boolean canChangeableTo(CertificationCreateRequestStatus status) {
      return status == FAILED;
    }
  },

  // 프로세스 실패함
  FAILED {
    @Override
    public boolean canChangeableTo(CertificationCreateRequestStatus status) {
      return true;
    }
  }
  ;

  public boolean isSuccess() {
    return this == COMPLETED;
  }
  public boolean isFail() {
    return this == FAILED;
  }
  public boolean canRequest() {
    return this == STARTED;
  }
  public boolean canCreateOrUpdateCertification() {
    return this == REQUESTED;
  }
  public abstract boolean canChangeableTo(CertificationCreateRequestStatus status);

}