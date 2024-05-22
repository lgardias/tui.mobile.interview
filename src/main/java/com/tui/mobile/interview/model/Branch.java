package com.tui.mobile.interview.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Branch {
    String name;
    String lastCommitSHA;
}
