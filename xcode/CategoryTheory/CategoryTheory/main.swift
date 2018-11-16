//
//  main.swift
//  CategoryTheory
//
//  Created by 田村孝文 on 2018/10/13.
//  Copyright © 2018 田村孝文. All rights reserved.
//

import Foundation

// 「iOSDC Japan 2018 「圏論とSwiftへの応用」発表スライドメモ」を参考
// https://qiita.com/inamiy/items/3e0c10d5eaf234b41c3d

// 恒等射
func id<A>(_ a:A)->A {
    return a
}

// 関数の合成
infix operator ∘
func ∘ <A,B,C>(_ g:@escaping((B) -> C), _ f: @escaping((A) -> B)) -> ((A) -> C) {
    return { g(f($0)) }
}

// 関数の合成のお試し
func count(_ s:String)->Int {
    return s.count
}
func isEven(_ i:Int)->Bool {
    return i%2 == 0
}

let 合成関数 = isEven ∘ count
print( 合成関数("hoge") )  // 4文字だからtrueになる
print( 合成関数("hoge2") ) // 5文字だからfalseになる

print( (count ∘ id)("hoge"))  // どっちも4になる
print( (id ∘ count)("hoge"))  // = 「結合律」


// 射: StringからInt(文字数カウント)
func f(_ s:String)->Int {
    return s.count
}

// 関手F: Arrayで包む
func F<A,B>(_ f:@escaping((A)->B)) -> (Array<A>) -> Array<B> {
    return {arr in
        arr.map{f($0)}
    }
}
// 関手G: Setで包む
func G<A,B>(_ f:@escaping((A)->B)) -> (Set<A>) -> Set<B> {
    return {set in
        Set(set.map{f($0)})
    }
}

// 自然変換alpha: ArrayからSetに変換
func alpha<T>(_ a:Array<T>)->Set<T> {
    return Set(a)
}
// (alphaA,alphaBは型を指定しているだけで、なくても行けた)
func alphaA(_ a:Array<String>)->Set<String>{
    return alpha(a)
}
func alphaB(_ a:Array<Int>)->Set<Int> {
    return alpha(a)
}

print((alphaB ∘ F(f))(["hoge","3","hehehe","abs","","nanigasi","7343"]))  // 上と下は同じ
print((G(f) ∘ alphaA)(["hoge","3","hehehe","abs","","nanigasi","7343"]))  // =可換図式

print(alphaB(F(f)(["hoge","3","hehehe"])))
print(G(f)(alphaA(["hoge","3","hehehe"])))



