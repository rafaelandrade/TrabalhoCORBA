package DepositApp;

/**
* DepositApp/DepositHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Deposit.idl
* Sunday, December 9, 2018 9:18:42 PM BRST
*/

public final class DepositHolder implements org.omg.CORBA.portable.Streamable
{
  public DepositApp.Deposit value = null;

  public DepositHolder ()
  {
  }

  public DepositHolder (DepositApp.Deposit initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = DepositApp.DepositHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    DepositApp.DepositHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return DepositApp.DepositHelper.type ();
  }

}
