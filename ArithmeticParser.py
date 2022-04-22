# @author Keenan Leverty

import re

ll1_table = [
#            +       -       *       /       (       )       double  $
             [""     ,""     ,""     ,""     ,"rT"   ,""     ,"rT"   ,""],  # t->
             ["+rT"  ,"-rT"  ,""     ,""     ,""     ,"e"    ,""     ,"e"], # T->
             [""     ,""     ,""     ,""     ,"vR"   ,""     ,"vR"   ,""],  # r->
             ["e"    ,"e"    ,"*vR"  ,"/vR"  ,""     ,"e"    ,""     ,"e"], # R->
             [""     ,""     ,""     ,""     ,"(t)"  ,""     ,"d"    ,""]   # v->
]

# input_exp = input("\n\n\nEnter a arithmetic expression to test\n")
input_exp = "4-0+5*(5*9)/2"
math_exp = re.sub("(\\d+\\.?\\d*|\\.\\d+)", "d", input_exp)

valid = 1
stack = ['$', 'T', 'r']

for i in range(len(math_exp)) :
    popTop = stack.pop()
    indexChar = math_exp[i]
    column = -1
    row = -1
    
    if indexChar == '+' : column = 0
    elif indexChar == '-' : column = 1
    elif indexChar == '*' : column = 2
    elif indexChar == '/' : column = 3
    elif indexChar == '(' : column = 4
    elif indexChar == ')' : column = 5
    elif indexChar == 'd' : column = 6
    elif indexChar == '$' : column = 7
    else :
        print("bad character entered")
        valid = 0

    while indexChar != popTop and valid == 1 :
        if popTop == 't' : row = 0
        elif popTop == 'T' : row = 1
        elif popTop == 'r' : row = 2
        elif popTop == 'R' : row = 3
        elif popTop == 'v' : row = 4
        else :
            print("Invalid Expression - 1")
            valid = 0
        
        pushString = ll1_table[row][column]

        if len(pushString) == 0 :
            print("Invalid Expression - 2")
            valid = 0
            break

        if len(stack) == 0 :
            print("Invalid Expression - 3")
            valid = 0
            break

        if pushString[0] != 'e' :
            index = len(pushString) - 1
            while index >= 0 :
                stack.append(pushString[index])
                index -= 1

        popTop = stack.pop()

    if(not bool(valid)) : break

if(bool(valid)) : print("The expression is valid")
else : print("The expression is invalid")
