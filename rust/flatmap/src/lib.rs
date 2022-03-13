pub fn flat_max(target: Vec<Vec<i32>>) -> i32 {
    *target.iter().flat_map(|v| v).max().unwrap_or(&0)
}
