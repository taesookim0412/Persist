export interface Job {
  [index:number]:{
    file: string,
    status: string,
    date: Date
  }
}
