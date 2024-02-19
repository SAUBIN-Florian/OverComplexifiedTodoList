export interface Todolist {
    id: string;
    title: string;
    color: string;
    todos: Todo[]
}

export interface Todo {
    id: number,
    task: string,
    done: boolean
}