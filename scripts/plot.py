import csv
import os
import sys

import matplotlib
matplotlib.use("Agg")
import matplotlib.pyplot as plt

results = sys.argv[1] if len(sys.argv) > 1 else "results"
plots = sys.argv[2] if len(sys.argv) > 2 else "plots"
os.makedirs(plots, exist_ok=True)


def read(name):
    with open(os.path.join(results, name)) as f:
        return list(csv.DictReader(f))


dp = read("dp.csv")
plt.figure(figsize=(6, 4))
for algo in ["LCS", "EditDistance"]:
    rows = [r for r in dp if r["algorithm"] == algo]
    plt.plot([int(r["n"]) for r in rows], [float(r["avg_ms"]) for r in rows], marker="o", label=algo)
plt.xlabel("string length n (both strings)")
plt.ylabel("average time, ms")
plt.title("LCS and Edit Distance")
plt.grid(alpha=0.3)
plt.legend()
plt.tight_layout()
plt.savefig(os.path.join(plots, "dp_time.png"), dpi=120)

matching = read("matching.csv")
cases = ["present", "absent", "repetitive"]
fig, axes = plt.subplots(1, 3, figsize=(13, 4), sharey=False)
for ax, case in zip(axes, cases):
    for algo in ["Naive", "KMP", "Rabin-Karp"]:
        rows = [r for r in matching if r["algorithm"] == algo and r["case"] == case]
        ax.plot([int(r["n"]) for r in rows], [float(r["avg_ms"]) for r in rows], marker="o", label=algo)
    ax.set_title(f"pattern {case}" if case != "repetitive" else "repetitive text (a...a / a..ab)")
    ax.set_xlabel("text length n")
    ax.set_ylabel("average time, ms")
    ax.grid(alpha=0.3)
    ax.legend()
plt.tight_layout()
plt.savefig(os.path.join(plots, "matching_time.png"), dpi=120)

kasai = read("kasai.csv")
plt.figure(figsize=(6, 4))
plt.plot([int(r["n"]) for r in kasai], [float(r["avg_ms"]) for r in kasai], marker="o", color="tab:green")
plt.xlabel("string length n")
plt.ylabel("average time, ms")
plt.title("Kasai LCP")
plt.grid(alpha=0.3)
plt.tight_layout()
plt.savefig(os.path.join(plots, "kasai_time.png"), dpi=120)
