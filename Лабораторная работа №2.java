class Stack { //создадили класс Stack, объявили нужные для работы поля, а далее инициализировали их в конструкторе
    private int mSize; //mSize - максимальный размер
    private int[] stackArray;
    private int top;

    public Stack(int m) {
        this.mSize = m;
        stackArray = new int[mSize];
        top = -1;
    }

    public void addElement(int element) {
        stackArray[++top] = element; //Увеличиваем индекс массива и добавляем на указанную позицию переданный элемент.
    }

    public int deleteElement() {
        return stackArray[top--]; //метод, которых обеспечит удаление элемента (с top позиции). Для удаления существующего top элемента достаточно уменьшить индекс массива. Таким образом нашей вершиной стека станет предпоследней элемент, а последний элемент будет удален.
    }

    public int readTop() { //Метод возвращает пользователю элемент, который находится на вершине стека.
        return stackArray[top];

    }

    public boolean isEmpty() { //Возвращает true (если массив данных пустой, индекс элемента top = 1, именно для этого мы задавали в конструкторе такое начальное значение для данной переменной).
        return (top == -1);
    }

    public boolean isFull() { //Метод возвращает значение true (в той ситуации, когда наш массив данных полностью заполнен и нет возможности добавить еще один элемент).
        return (top == mSize - 1);
    }
}

public class AwesomeStack { 

    public static void main(String[] args) { //введения четырех элементов, удаление одного и дальнейший вывод стека на экран.
        Stack mStack = new Stack(10);

        mStack.addElement(79);
        mStack.addElement(59);
        mStack.addElement(35);
        mStack.addElement(24);

        mStack.deleteElement();

        System.out.print("Стек: ");
        while (!mStack.isEmpty()) {
            int value = mStack.deleteElement();
            System.out.print(value);
            System.out.print(" ");
        }
        System.out.println("");
    }
}