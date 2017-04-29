make clean
make

rm results.txt

echo "1 fifo d" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 1 fifo d  >> results.txt
echo "2 fifo d" | tr '\n' ','>> results.txt
java Driver programlist programtrace 2 fifo d  >> results.txt
echo "4 fifo d" | tr '\n' ','>> results.txt
java Driver programlist programtrace 4 fifo d  >> results.txt
echo "8 fifo d" | tr '\n' ','>> results.txt
java Driver programlist programtrace 8 fifo d  >> results.txt
echo "16 fifo d" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 16 fifo d >> results.txt

echo "1 lru d" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 1 lru d >> results.txt
echo "2 lru d" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 2 lru d >> results.txt
echo "4 lru d" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 4 lru d >> results.txt
echo "8 lru d" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 8 lru d >> results.txt
echo "16 lru d" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 16 lru d >> results.txt

echo "1 sclru d" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 1 sclru d >> results.txt
echo "2 sclru d" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 2 sclru d >> results.txt
echo "4 sclru d" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 4 sclru d >> results.txt
echo "8 sclru d" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 8 sclru d >> results.txt
echo "16 sclru d" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 16 sclru d >> results.txt


echo "1 fifo p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 1 fifo p >> results.txt
echo "2 fifo p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 2 fifo p >> results.txt
echo "4 fifo p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 4 fifo p >> results.txt
echo "8 fifo p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 8 fifo p >> results.txt
echo "16 fifo p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 16 fifo p >> results.txt

echo "1 lru p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 1 lru p >> results.txt
echo "2 lru p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 2 lru p >> results.txt
echo "4 lru p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 4 lru p >> results.txt
echo "8 lru p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 8 lru p >> results.txt
echo "16 lru p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 16 lru p >> results.txt

echo "1 sclru p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 1 sclru p >> results.txt
echo "2 sclru p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 2 sclru p >> results.txt
echo "4 sclru p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 4 sclru p >> results.txt
echo "8 sclru p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 8 sclru p >> results.txt
echo "16 sclru p" | tr '\n' ',' >> results.txt
java Driver programlist programtrace 16 sclru p >> results.txt
