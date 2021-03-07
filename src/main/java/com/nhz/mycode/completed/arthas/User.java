package com.nhz.mycode.completed.arthas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public String userId;
    public String userName;
    public String userAge;
    public String userSex;
    public String userPhone;
}
