# @author Keenan Leverty

ll_table = Array[
    #            +       -       *       /       (       )       double  $
    [""     ,""     ,""     ,""     ,"rT"   ,""     ,"rT"   ,""],  # t->
    ["+rT"  ,"-rT"  ,""     ,""     ,""     ,"e"    ,""     ,"e"], # T->
    [""     ,""     ,""     ,""     ,"vR"   ,""     ,"vR"   ,""],  # r->
    ["e"    ,"e"    ,"*vR"  ,"/vR"  ,""     ,"e"    ,""     ,"e"], # R->
    [""     ,""     ,""     ,""     ,"(t)"  ,""     ,"d"    ,""]   # v->
]

DIGIT = /(\\d+\\.?\\d*|\\.\\d+)/

puts "Enter an arithmetic expression"
inputExpr = gets

inputExpr.gsub(DIGIT, 'd')

stack = ['$', 'T', 'r']

for i in inputExpr.length
    popTop = stack.pop
    indexChar = inputExpr[i]
    column = -1
    row = -1
    pushString = ""
    valid = true

    case indexChar
    when '+'
        column = 0
    when '-'
        column = 1
    when '*'
        column = 2
    when '/'
        column = 3
    when '('
        column = 4
    when ')'
        column = 5
    when 'd'
        column = 6
    when '$'
        column = 7
    else
        puts "ERROR: Invalid Input"
        column = -1
        valid = false
    end

    while indexChar != popTop
        case popTop
        when 't'
            column = 0
        when 'T'
            column = 1
        when 'r'
            column = 2
        when 'R'
            column = 3
        when 'v'
            column = 4
        else
            puts "ERROR: Invalid Input"
            column = -1
            valid = false
        end

        pushString = ll_table[row][column]

        if pushString.length == 0
            puts "ERROR: Invalid arithmetic expression"
            valid = false
            break
        end

        if stack.length == 0
            puts "ERROR: Invalid arithmetic expression"
            valid = false
            break
        end

        if pushString[0] != 'e'
            j = pushString.length
            while j >= 0
                stack.push(pushString[j])
            end
        end

        popTop = stack_machine.pop()
        
    end

end

if valid
    puts "String is a valid arithmetic expression"
end
