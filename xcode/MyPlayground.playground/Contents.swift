//: Playground - noun: a place where people can play


infix operator &&&

func &&& <A,B,C>(_ g:@escaping((B) -> C), _ f: @escaping((A) -> B)) -> ((A) -> C) {
    return { g(f($0)) }
}

func _count(s:String)->Int {
    return s.count
}
func _isEven(i:Int)->Bool {
    return i%2 == 0
}

let 合成関数 = _isEven &&& _count
print("hoge")
print( 合成関数("hoge") )
print( 合成関数("hoge2") )





