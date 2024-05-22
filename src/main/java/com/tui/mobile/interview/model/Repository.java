package com.tui.mobile.interview.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Repository {
    String name;
    String ownerLogin;
    List<Branch> branches;
}
