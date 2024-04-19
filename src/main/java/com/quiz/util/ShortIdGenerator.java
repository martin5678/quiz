package com.quiz.util;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hashids.Hashids;

public class ShortIdGenerator implements IdentifierGenerator, Configurable {
  static final Integer DEFAULT_LENGTH = 22;
  private Integer length;

  @Override
  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws
      MappingException {
    if (params.get("length") != null) {
      length = Integer.parseInt((String) params.get("length"));
    } else {
      length = DEFAULT_LENGTH;
    }
  }

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object) throws
      HibernateException {
    return generateOne(length);
  }

  public static String generateOne(Integer length) {
    return generateOneMixedCase(length).toLowerCase();
  }

  public static String generateOneMixedCase(Integer length) {
    UUID uuid = UUID.randomUUID();
    Hashids hashids = new Hashids();
    long mask = 0x1FFFFFFFFFFFFFL;
    long n1 = Math.abs(uuid.getMostSignificantBits() & mask);
    long n2 = Math.abs(uuid.getLeastSignificantBits() & mask);
    String ret = hashids.encode(n1) + hashids.encode(n2);
    if (length == null) {
      length = DEFAULT_LENGTH;
    }
    return ret.substring(0, Math.min(length, ret.length()));
  }
}