package store.zone.enums;

public enum BillStatus {
  WAIT_STORE_CONFIRM((short) 1),
  STORE_CONFIRM((short) 2),
  DELIVERED((short) 3),
  CANCEL((short) 4);

  private Short status;

  BillStatus(Short status) {
    this.status = status;
  }

  public Short getValue(){
    return status;
  }

  public String getString(){
    switch (status){
      case 1:{
        return "wait store confirm";
      }
      case 2:{
        return "store confirm";
      }
      case 3:{
        return "delivered";
      }
      case 4:{
        return "cancel";
      }
    }
    return null;
  }
}
