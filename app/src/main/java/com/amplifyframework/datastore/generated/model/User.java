package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byCompany", fields = {"companyID"})
public final class User implements Model {
  public static final QueryField ID = field("User", "id");
  public static final QueryField NAME = field("User", "name");
  public static final QueryField LICENSE_PLATE = field("User", "licensePlate");
  public static final QueryField COMPANY = field("User", "companyID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String", isRequired = true) String licensePlate;
  private final @ModelField(targetType="Company") @BelongsTo(targetName = "companyID", type = Company.class) Company company;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getLicensePlate() {
      return licensePlate;
  }
  
  public Company getCompany() {
      return company;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private User(String id, String name, String licensePlate, Company company) {
    this.id = id;
    this.name = name;
    this.licensePlate = licensePlate;
    this.company = company;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getName(), user.getName()) &&
              ObjectsCompat.equals(getLicensePlate(), user.getLicensePlate()) &&
              ObjectsCompat.equals(getCompany(), user.getCompany()) &&
              ObjectsCompat.equals(getCreatedAt(), user.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), user.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getLicensePlate())
      .append(getCompany())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("licensePlate=" + String.valueOf(getLicensePlate()) + ", ")
      .append("company=" + String.valueOf(getCompany()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static User justId(String id) {
    return new User(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      licensePlate,
      company);
  }
  public interface NameStep {
    LicensePlateStep name(String name);
  }
  

  public interface LicensePlateStep {
    BuildStep licensePlate(String licensePlate);
  }
  

  public interface BuildStep {
    User build();
    BuildStep id(String id);
    BuildStep company(Company company);
  }
  

  public static class Builder implements NameStep, LicensePlateStep, BuildStep {
    private String id;
    private String name;
    private String licensePlate;
    private Company company;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          name,
          licensePlate,
          company);
    }
    
    @Override
     public LicensePlateStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep licensePlate(String licensePlate) {
        Objects.requireNonNull(licensePlate);
        this.licensePlate = licensePlate;
        return this;
    }
    
    @Override
     public BuildStep company(Company company) {
        this.company = company;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, String licensePlate, Company company) {
      super.id(id);
      super.name(name)
        .licensePlate(licensePlate)
        .company(company);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder licensePlate(String licensePlate) {
      return (CopyOfBuilder) super.licensePlate(licensePlate);
    }
    
    @Override
     public CopyOfBuilder company(Company company) {
      return (CopyOfBuilder) super.company(company);
    }
  }
  
}
