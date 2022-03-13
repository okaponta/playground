use criterion::{criterion_group, criterion_main, Criterion};

fn flatmap_bench(c: &mut Criterion) {
    let dist = vec![
        vec![2, 4, 5],
        vec![1, 3, 6],
        vec![1, 7, 9],
        vec![1, 13, 6],
        vec![1, 7, 9],
        vec![1, 13, 14],
        vec![1, 7, 9],
        vec![1, 13, 17],
        vec![1, 7, 9],
        vec![19, 13, 6],
        vec![1, 13, 14],
        vec![1, 7, 9],
        vec![1, 13, 17],
        vec![1, 7, 9],
        vec![19, 13, 6],
    ];
    c.bench_function("flatmap", |b| {
        b.iter(|| {
            let m = dist.iter().flat_map(|v| v).max().unwrap_or(&0);
            assert!(m == &19);
        })
    });
}

fn map_bench(c: &mut Criterion) {
    let dist = vec![
        vec![2, 4, 5],
        vec![1, 3, 6],
        vec![1, 7, 9],
        vec![1, 13, 6],
        vec![1, 7, 9],
        vec![1, 13, 14],
        vec![1, 7, 9],
        vec![1, 13, 17],
        vec![1, 7, 9],
        vec![19, 13, 6],
        vec![1, 13, 14],
        vec![1, 7, 9],
        vec![1, 13, 17],
        vec![1, 7, 9],
        vec![19, 13, 6],
    ];
    c.bench_function("map", |b| {
        b.iter(|| {
            let m = dist
                .iter()
                .map(|v| v.iter().max().unwrap_or(&0))
                .max()
                .unwrap_or(&0);
            assert!(m == &19);
        })
    });
}

criterion_group!(benches, flatmap_bench, map_bench);
criterion_main!(benches);
