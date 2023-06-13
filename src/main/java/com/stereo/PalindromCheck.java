package com.stereo;

public class PalindromCheck {
    private PalindromCheckService palindromCheckService;

    public PalindromCheck(PalindromCheckService palindromCheckService) {
        this.palindromCheckService = palindromCheckService;
    }

    public boolean isPalindrome(String str) {
        // Використовуємо palindromCheckService для перевірки, чи є рядок паліндромом
        return palindromCheckService.isPalindrome(str);
    }

}